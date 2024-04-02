// Sample user data
export const users = [{
      username: 'User1',
      profilePicture: 'https://i.imgur.com/JZyjvRB.png'
   },
   {
      username: 'User2',
      profilePicture: 'https://i.imgur.com/JZyjvRB.png'
   },
   {
      username: 'User3',
      profilePicture: 'https://i.imgur.com/JZyjvRB.png'
   },
];

// Sample comments data
export let comments = [{
      id: 1,
      username: 'User1',
      profilePicture: 'https://i.imgur.com/JZyjvRB.png',
      text: 'This is a great video!',
      likes: 10,
      timestamp: new Date('2024-03-15T12:00:00'),
      liked: false
   },
   {
      id: 2,
      username: 'User2',
      profilePicture: 'https://i.imgur.com/JZyjvRB.png',
      text: 'Awesome content!',
      likes: 5,
      timestamp: new Date('2024-03-15T10:00:00'),
      liked: false
   },
   // Add more comments as needed
];

//// Function to add a new comment
//function addComment() {
//   const usernameInput = document.getElementById('username');
//   const commentInput = document.getElementById('comment');
//
//   if (usernameInput.value && commentInput.value) {
//      const newComment = {
//         id: comments.length + 1,
//         username: usernameInput.value,
//         profilePicture: 'https://i.imgur.com/JZyjvRB.png',
//         text: commentInput.value,
//         likes: 0,
//         timestamp: new Date(),
//         liked: false,
//      };
//
//      // Insert the new comment at the beginning of the comments array
//      comments.unshift(newComment);
//
//      renderComments();
//      // Clear input fields
//      usernameInput.value = '';
//      commentInput.value = '';
//   }
//}

//function renderComments() {
//   const commentList = document.getElementById('commentList');
//   commentList.innerHTML = '';
//
//   comments.forEach(comment => {
//      const listItem = document.createElement('li');
//      listItem.classList.add('comment');
//
//      const userAvatar = document.createElement('img');
//      userAvatar.src = comment.profilePicture;
//      userAvatar.alt = 'Profile Picture';
//      userAvatar.classList.add('avatar'); // Add a CSS class for styling
//
//      const commentInfo = document.createElement('div');
//      commentInfo.classList.add('comment-info');
//
//      const username = document.createElement('p');
//      username.textContent = comment.username;
//
//      const commentText = document.createElement('p');
//      commentText.textContent = comment.text;
//
//      commentInfo.appendChild(username);
//      commentInfo.appendChild(commentText);
//
//      const likes = document.createElement('span');
//      likes.classList.add('likes-btn');
//      const thumbsUpBtn = document.createElement('button');
//      thumbsUpBtn.classList.add('thumbs-up-btn');
//      thumbsUpBtn.onclick = () => likeComment(comment.id);
//
//      // Toggle filled/outline appearance
//      if (comment.liked) {
//         thumbsUpBtn.innerHTML = `<img src="https://img.icons8.com/ios-filled/24/FFFFFF/thumb-up--v1.png">`;
//      } else {
//         thumbsUpBtn.innerHTML = '<img src="https://img.icons8.com/ios/24/FFFFFF/thumb-up--v1.png"/>';
//      }
//
//      likes.appendChild(thumbsUpBtn);
//      likes.appendChild(document.createTextNode(` ${comment.likes} Likes`));
//
//      const actions = document.createElement('div');
//      actions.classList.add('actions');
//
//      const editButton = document.createElement('button');
//      editButton.classList.add('edit-btn');
//      editButton.innerHTML = '<img src="https://img.icons8.com/material/15/FAB005/edit--v1.png"style="padding-top: 5px; padding-bottom: 5px; padding-left: 5px;"/>'
//      editButton.onclick = () => editComment(comment.id);
//
//      const deleteButton = document.createElement('button');
//      deleteButton.classList.add('delete-btn');
//      deleteButton.innerHTML = '<img src="https://img.icons8.com/material-rounded/15/FA5252/delete-sign.png"style="padding-top: 5px; padding-bottom: 5px; padding-left: 5px;"/>'
//      deleteButton.onclick = () => deleteComment(comment.id);
//
//      actions.appendChild(editButton);
//      actions.appendChild(deleteButton);
//
//      listItem.appendChild(userAvatar);
//      listItem.appendChild(commentInfo);
//      listItem.appendChild(likes);
//      listItem.appendChild(actions);
//
//      commentList.appendChild(listItem);
//   });
//
//   // Update the heading with the number of comments
//   const commentsHeading = document.getElementById('commentsHeading');
//   commentsHeading.innerHTML = `<u><h1>Comments (${comments.length})</h1></u>`;
//}


//// Function to like a comment
//function likeComment(commentId) {
//   const comment = comments.find(c => c.id === commentId);
//
//   if (comment) {
//      // Toggle filled/outline appearance
//      if (comment.liked) {
//         comment.likes--;
//      } else {
//         comment.likes++;
//      }
//
//      comment.liked = !comment.liked;
//
//      renderComments();
//   }
//}

// Function to change tab (sort comments)
//function changeTab(tab) {
//   const tabs = document.querySelectorAll('.tab');
//   tabs.forEach(t => t.classList.remove('active'));
//
//   const selectedTab = document.querySelector(`.tab[data-tab="${tab}"]`);
//   selectedTab.classList.add('active');
//
//   if (tab === 'recent') {
//      comments.sort((a, b) => b.timestamp - a.timestamp);
//   } else if (tab === 'popular') {
//      comments.sort((a, b) => b.likes - a.likes);
//   }
//
//   renderComments();
//}
//
//// Function to edit a comment
//function editComment(commentId) {
//   const comment = comments.find(c => c.id === commentId);
//
//   if (comment) {
//      const updatedText = prompt('Edit your comment:', comment.text);
//
//      if (updatedText !== null) {
//         comment.text = updatedText;
//         renderComments();
//      }
//   }
//}
//
//// Initial render
//renderComments();

const updateProperties = (elem, state) => {

   elem.style.setProperty('--x', `${ state.x }px`)
   elem.style.setProperty('--y', `${ state.y }px`)
   elem.style.setProperty('--width', `${ state.width }px`)
   elem.style.setProperty('--height', `${ state.height }px`)
   elem.style.setProperty('--radius', state.radius)
   elem.style.setProperty('--scale', state.scale)

}

document.querySelectorAll('.cursor').forEach((cursor) => {

   let onElement

   const createState = (e) => {

      const defaultState = {
         x: e.clientX,
         y: e.clientY,
         width: 42,
         height: 42,
         radius: '100px'
      }

      const computedState = {}

      if (onElement != null) {
         const {
            top,
            left,
            width,
            height
         } = onElement.getBoundingClientRect()
         const radius = window.getComputedStyle(onElement).borderTopLeftRadius

         computedState.x = left + width / 2
         computedState.y = top + height / 2
         computedState.width = width
         computedState.height = height
         computedState.radius = radius
      }

      return {
         ...defaultState,
         ...computedState
      }

   }

   document.addEventListener('mousemove', (e) => {
      const state = createState(e)
      updateProperties(cursor, state)
   })

   document.body.addEventListener('mouseover', (e) => {
      const targetElement = e.target.closest('a, button, .btn, .tab, .comment-box input, .comment-box textarea');
      if (targetElement) {
         onElement = targetElement;
      }
   });

   document.body.addEventListener('mouseout', (e) => {
      const targetElement = e.target.closest('a, button, .btn, .tab, .comment-box input, .comment-box textarea');
      if (targetElement === onElement) {
         onElement = undefined;
      }
   });


})