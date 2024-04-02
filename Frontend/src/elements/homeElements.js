const form = document.querySelector('.search-form');
const imageResults = document.querySelector('.image-container');

// Prevent default so that the form doesnt submit and try to go to another link.
form.addEventListener('submit', async function (e) {
    e.preventDefault();

    // Search value.
    const searchTerm = form.elements.query.value;

    // Parameters for the search. This is so we can add more headers and stuff. Check the API docs if you want to see more.
    const config = { params: { q: searchTerm } };
    const res = await axios.get(' https://api.tvmaze.com/search/shows', config);

    // Res.data returns the data from the API.
    addImages(res.data);

    //   If nothing is searched it will show 'No Results Found' message
    if (searchTerm === '') {
        const heading = document.createElement('div');
        const headingText = document.createElement('p');
        headingText.innerText = 'No Results Found';
        heading.append(headingText);
        heading.classList.add('result-heading');
        imageResults.append(heading);
    }
});

// Function that accepts data as argument and creates images and appends them to the result-container div.
const addImages = (shows) => {
    // Clears the previous results if any.
    imageResults.innerHTML = '';

    //  Heading that shows 'top results'. Appending it to the heading div we create below.
    const heading = document.createElement('div');
    const headingText = document.createElement('p');
    headingText.innerText = 'Top Results';
    //   Appends the heading to the heading div and that div to the container div.
    heading.append(headingText);
    heading.classList.add('result-heading');
    imageResults.append(heading);

    //   Creating the result container
    const resultContainer = document.createElement('div');
    resultContainer.classList.add('result-container');

    // Iterates over the array of shows and creates a div with elements for each of them.
    for (let result of shows) {
        // If the show searched has an image then it will do the following.
        if (result.show.image) {
            //  Appending image results to the div created above.
            const imageDiv = document.createElement('div');
            imageDiv.classList.add('image-result');
            const image = document.createElement('img');
            // Src is taken from the API response of this show.
            image.src = result.show.image.medium;

            //   Creates the div for the image overlay.
            const overlayDiv = document.createElement('div');
            overlayDiv.classList.add('overlay');

            //   Creates the div for the title in the overlay.
            const overlayTitleDiv = document.createElement('div');
            overlayTitleDiv.classList.add('overlay-title');
            const title = document.createElement('p');
            //   Names the show accordingly by the data received from the API.
            title.innerText = result.show.name;

            //  Appends the title to the overlay title div and appends that div to the overlay div.
            overlayTitleDiv.append(title);
            overlayDiv.append(overlayTitleDiv);

            //   Creates an overlay div for the rating.
            const overlayRatingDiv = document.createElement('div');
            overlayRatingDiv.classList.add('overlay-rating');
            const rating = document.createElement('p');
            overlayRatingDiv.appendChild(rating);

            //  Some shows are yet to be rated or have not been rated. So if the data is null it will say not yet rated.
            if (result.show.rating.average === null) {
                rating.innerText = `Not Rated Yet`;
            } else {
                rating.innerText = `Rating: ${result.show.rating.average}`;
            }
            // Appends the rating div to the overlay div.
            overlayDiv.append(overlayRatingDiv);
            // Appends the image to the image div. And then the image div to the result container. This whole function is done for all of the shows found when searched.
            imageDiv.append(image);
            imageDiv.append(overlayDiv);
            resultContainer.append(imageDiv);
        }

        // The image results is a div which has everything in it and it is then appended to the result container.
        imageResults.append(resultContainer);
    }
};

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
            const { top, left, width, height } = onElement.getBoundingClientRect()
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
        const targetElement = e.target.closest('a, button, #search, .image-result');
        if (targetElement) {
            onElement = targetElement;
        }
    });

    document.body.addEventListener('mouseout', (e) => {
        const targetElement = e.target.closest('a, button, #search, .image-result');
        if (targetElement === onElement) {
            onElement = undefined;
        }
    });


})
