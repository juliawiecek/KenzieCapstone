import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import CommentControllerClient from "../api/CommentControllerClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class CommentPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet', 'onCreateComment', 'onGetComments', 'renderComments'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the comments list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.querySelector('submit-btn').addEventListener('click', this.onCreateComment);
        await this.onGetComments();
        this.client = new CommentControllerClient();

        this.dataStore.addChangeListener(this.renderComments)
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

                const userAvatar = document.createElement('img');
                userAvatar.src = comment.profilePicture;
                userAvatar.alt = 'Profile Picture';
                userAvatar.classList.add('avatar');

                const commentInfo = document.createElement('div');
                commentInfo.classList.add('comment-info');

                const username = document.createElement('p');
                username.textContent = comment.username;

                const commentText = document.createElement('p');
                commentText.textContent = comment.text;

                commentInfo.appendChild(username);
                commentInfo.appendChild(commentText);

                const likes = document.createElement('span');
                likes.classList.add('likes-btn');
                const thumbsUpBtn = document.createElement('button');
                thumbsUpBtn.classList.add('thumbs-up-btn');
                thumbsUpBtn.onclick = () => getLikesComment(comment.id);

                // Toggle filled/outline appearance
                if (comment.liked) {
                    thumbsUpBtn.innerHTML = `<img src="https://img.icons8.com/ios-filled/24/FFFFFF/thumb-up--v1.png">`;
                } else {
                    thumbsUpBtn.innerHTML = '<img src="https://img.icons8.com/ios/24/FFFFFF/thumb-up--v1.png"/>';
                }

                likes.appendChild(thumbsUpBtn);
                likes.appendChild(document.createTextNode(` ${comment.likes} Likes`));

                const actions = document.createElement('div');
                actions.classList.add('actions');

                const editButton = document.createElement('button');
                editButton.classList.add('edit-btn');
                editButton.innerHTML = '<img src="https://img.icons8.com/material/15/FAB005/edit--v1.png"style="padding-top: 5px; padding-bottom: 5px; padding-left: 5px;"/>'
                editButton.onclick = () => editComment(comment.id);

                const deleteButton = document.createElement('button');
                deleteButton.classList.add('delete-btn');
                deleteButton.innerHTML = '<img src="https://img.icons8.com/material-rounded/15/FA5252/delete-sign.png"style="padding-top: 5px; padding-bottom: 5px; padding-left: 5px;"/>'
                deleteButton.onclick = () => deleteComment(comment.id);

                actions.appendChild(editButton);
                actions.appendChild(deleteButton);

                listItem.appendChild(userAvatar);
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

    async onGetComments() {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        this.dataStore.set("comments", null);

        let result = await this.client.getAllComments(this.errorHandler);
        this.dataStore.set("comments", result);
        if (result) {
            renderComments();
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async getLikesComment(commentId) {
    try {
        const comment = comments.find(c => c.id === commentId);

        if (comment) {
            // Toggle filled/outline appearance
            if (!comment.liked) {
                await this.client.likeComment(commentId, this.errorHandler);
                comment.likes++;
                comment.liked = true;
                // Update likes in the datastore
                this.dataStore.set(`comment_${commentId}_likes`, comment.likes);
            } else {
                await this.client.unLikeComment(commentId, this.errorHandler);
                comment.likes--;
                comment.liked = false;
                // Update likes in the datastore
                this.dataStore.set(`comment_${commentId}_likes`, comment.likes);
            }

            renderComments();
        }
    } catch (error) {
        console.error("Error occurred while liking/unliking comment:", error);
        // Handle error as needed
    }
}

    async onCreateComment(event) {
        // Prevent the page from refreshing on form submitk
        event.preventDefault();
        this.dataStore.set("comment", null);

        let usernameInput = document.getElementById("username").value;
        let commentInput = document.getElementById("comment").value

        const createdComment = await this.client.createComment(usernameInput, commentInput, this.errorHandler);
        this.dataStore.set("comment", createdComment);

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

            renderComments();
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
