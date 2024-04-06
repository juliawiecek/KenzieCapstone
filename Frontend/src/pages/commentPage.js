import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CommentControllerClient from "../api/CommentControllerClient";
import { users, comments } from '../elements/commentElements.js';

/**
 * Logic needed for the view playlist page of the website.
 */
class CommentPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['createComment', 'renderComments', 'getAllComments', 'getLikesComment'], this);
        this.dataStore = new DataStore();
//        this.thumbsUpBtn = null;
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the comments list.
     */
    async mount() {
        document.getElementById('create-comment').addEventListener('click', (event) => this.createComment(event));
        //await this.getComments();
        this.client = new CommentControllerClient();

        try {
            const allComments = await this.client.getAllComments();
            console.log('Fetched comment list:', allComments);

            this.dataStore.set("comments", allComments);

            await this.renderComments();
        } catch (error) {
            console.error('Error fetching medication list:', error);
        }

        // Filter out deleted comments on page load
        window.addEventListener('load', () => {
            let deletedComments = JSON.parse(localStorage.getItem('deletedComments')) || [];

            // Remove deleted comments from the data store on page load
            deletedComments.forEach((deletedCommentId) => {
                delete this.dataStore[deletedCommentId];
            });

            // Optionally, you can also filter out deleted comments from being displayed on the page
            // For example, when rendering comments, exclude the deleted comments based on this information
        });
    }

    // Render Methods --------------------------------------------------------------------------------------------------

//    async renderExample() {
//        let resultArea = document.getElementById("result-info");
//
//        const example = this.dataStore.get("example");
//
//        if (example) {
//            resultArea.innerHTML = `
//                <div>ID: ${example.id}</div>
//                <div>Name: ${example.name}</div>
//            `
//        } else {
//            resultArea.innerHTML = "No Item";
//        }
//    }

        async renderComments() {
        const commentList = document.getElementById('commentList');

        const comments = this.dataStore.get("comments");

        if (comments) {
            commentList.innerHTML = '';

            comments.forEach(comment => {
                const listItem = document.createElement('li');
                listItem.classList.add('comment');

//                const userAvatar = document.createElement('img');
//                userAvatar.src = comment.profilePicture;
//                userAvatar.alt = 'Profile Picture';
//                userAvatar.classList.add('avatar');

                const commentInfo = document.createElement('div');
                commentInfo.classList.add('comment-info');

                const username = document.createElement('p');
                username.textContent = comment.userName;

                const commentText = document.createElement('p');
                commentText.textContent = comment.contents;

                commentInfo.appendChild(username);
                commentInfo.appendChild(commentText);

                const likes = document.createElement('span');
                likes.classList.add('likes-btn');
                const thumbsUpBtn = document.createElement('button');
                thumbsUpBtn.classList.add('thumbs-up-btn');
                //thumbsUpBtn.setAttribute('id', 'thumbs-up-btn-id');

                // Retrieve likesData from localStorage when the page is loaded
                let likesData = localStorage.getItem(`comment_${comment.commentId}_likes`) || 0;
                let liked = localStorage.getItem(`comment_${comment.commentId}_liked`) === 'true';

                // Toggle filled/outline appearance
                if (liked) {
                    thumbsUpBtn.innerHTML = `<img src="https://img.icons8.com/ios-filled/24/FFFFFF/thumb-up--v1.png">`;
                } else {
                    thumbsUpBtn.innerHTML = '<img src="https://img.icons8.com/ios/24/FFFFFF/thumb-up--v1.png"/>';
                }

                likes.appendChild(thumbsUpBtn);
                likes.appendChild(document.createTextNode(` ${likesData} Likes`));


                thumbsUpBtn.onclick = async () => {
                    // Toggle the liked status
                    let liked = localStorage.getItem(`comment_${comment.commentId}_liked`) === 'true';
                    liked = !liked;

                    // Update the appearance of thumbsUpBtn based on the liked status
                    thumbsUpBtn.innerHTML = liked ? `<img src="https://img.icons8.com/ios-filled/24/FFFFFF/thumb-up--v1.png">` : '<img src="https://img.icons8.com/ios/24/FFFFFF/thumb-up--v1.png"/>';

                    // Store the updated liked status in localStorage
                    localStorage.setItem(`comment_${comment.commentId}_liked`, liked.toString());

                    // Update the likes count based on the liked status
                    comment.likes = liked ? comment.likes + 1 : Math.max(comment.likes - 1, 0);

                    // Update the likes count in localStorage
                    this.dataStore.set(`comment_${comment.commentId}_likes`, comment.likes);

                    // Update the likes data on the UI
                    likesData = await this.client.likeComment(comment.commentId, comment.likes, this.errorHandler);
                    likes.lastChild.nodeValue = ` ${likesData.likes} Likes`;
                    localStorage.setItem(`comment_${comment.commentId}_likes`, likesData.likes);
                };

                const actions = document.createElement('div');
                actions.classList.add('actions');

                const editButton = document.createElement('button');
                editButton.classList.add('edit-btn');
                editButton.innerHTML = '<img src="https://img.icons8.com/material/15/FAB005/edit--v1.png"style="padding-top: 5px; padding-bottom: 5px; padding-left: 5px;"/>'
                editButton.onclick = async () => {
                    try {
                        const updatedText = prompt('Edit your comment:', comment.contents);

                        if (updatedText !== null) {
                            commentText.contents = updatedText;
                            commentText.textContent = updatedText;
                            await this.client.updateComment(comment.commentId, commentText.contents, this.errorHandler);
                        }
                    } catch (error) {
                        console.error('An error occurred:', error);
                    }
                }

                const deleteButton = document.createElement('button');
                deleteButton.classList.add('delete-btn');
                deleteButton.innerHTML = '<img src="https://img.icons8.com/material-rounded/15/FA5252/delete-sign.png"style="padding-top: 5px; padding-bottom: 5px; padding-left: 5px;"/>'
                deleteButton.onclick = async () => {
                    const selector = '[comment-id="' + comment.commentId + '"]';
                    const commentElement = document.querySelector(selector);
                    commentElement.remove();

                    // Store the deleted comment ID in local storage
                    let deletedComments = JSON.parse(localStorage.getItem('deletedComments')) || [];
                    deletedComments.push(comment.commentId);
                    localStorage.setItem('deletedComments', JSON.stringify(deletedComments));

                    // Remove the comment from the dataStore
                    delete this.dataStore[comment.commentId];

                    // Send a request to delete the comment on the server
                    await this.client.deleteComment(comment.commentId, this.errorHandler);
                }

                listItem.setAttribute('comment-id', comment.commentId);

                actions.appendChild(editButton);
                actions.appendChild(deleteButton);

//                listItem.appendChild(userAvatar);
                listItem.appendChild(commentInfo);
                listItem.appendChild(likes);
                listItem.appendChild(actions);

                commentList.appendChild(listItem);
            });

            // Update the heading with the number of comments
            const commentsHeading = document.getElementById('commentsHeading');
            commentsHeading.innerHTML = `<u><h1>Comments (${comments.length})</h1></u>`;

        } else {
            commentList.innerHTML = "No Comments";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async getAllComments() {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        this.dataStore.set("comments", null);

        let result = await this.client.getAllComments(this.errorHandler);
        this.dataStore.set("comments", result);
        if (result) {
            this.renderComments();
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async getLikesComment(comment, thumbsUpBtn, likes) {
    try {
        const comments = this.dataStore.get("comments");
        const currentComment = comments.find(c => c.commentId === comment.commentId);
        const textNode = Array.from(likes.childNodes).find(node => node.nodeType === Node.TEXT_NODE);

        if (currentComment && textNode) {
            if (thumbsUpBtn.innerHTML !== `<img src="https://img.icons8.com/ios-filled/24/FFFFFF/thumb-up--v1.png">`) {
                currentComment.likes++;
                currentComment.liked = true;
                thumbsUpBtn.innerHTML = `<img src="https://img.icons8.com/ios-filled/24/FFFFFF/thumb-up--v1.png">`;
            } else {
                currentComment.likes--;
                currentComment.liked = false;
                thumbsUpBtn.innerHTML = '<img src="https://img.icons8.com/ios/24/FFFFFF/thumb-up--v1.png"/>';
            }

            this.dataStore.set(`comment_${comment.commentId}_likes`, currentComment.likes);
            const likesData = await this.client.likeComment(comment.commentId, currentComment.likes, this.errorHandler);
            textNode.textContent = ` ${likesData.likes} Likes`;

            return likesData.likes; // Return just the likes value
        }
    } catch (error) {
        console.error("Error occurred while liking/unliking comment:", error);
        // Handle error as needed
    }
}

//    // Function to edit a comment
//    function editComment(commentId) {
//       const comment = comments.find(c => c.id === commentId);
//
//       if (comment) {
//          const updatedText = prompt('Edit your comment:', comment.text);
//
//          if (updatedText !== null) {
//             comment.text = updatedText;
//             renderComments();
//          }
//       }
//    }

    async createComment(event) {
        // Prevent the page from refreshing on form submitk
        event.preventDefault();
        this.dataStore.set("comment", null);

        let usernameInput = document.getElementById("username").value;
        let commentInput = document.getElementById("comment").value

        const createdComment = await this.client.createComment(usernameInput, commentInput, this.errorHandler);
        this.dataStore.set("comments", createdComment);

        if (createdComment) {
            const newComment = {
                id: comments.length + 1,
                username: usernameInput,
                profilePicture: 'https://i.imgur.com/JZyjvRB.png',
                text: commentInput,
                likes: 0,
                timestamp: new Date(),
                liked: false,
            };

            // Insert the new comment at the beginning of the comments array
            comments.unshift(newComment);

            try {
                await this.getAllComments(); // Call the renderComments function using await
            } catch (error) {
                console.error("Error occurred while rendering comments:", error);
            }
            // Clear input fields
            usernameInput = '';
            commentInput = '';
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const commentPage = new CommentPage();
    commentPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
