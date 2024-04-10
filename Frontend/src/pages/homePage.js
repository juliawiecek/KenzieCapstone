import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import ExampleClient from "../api/TVShowClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class HomePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['getPopularShows', 'getSearchedShow', 'renderShows'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
        document.getElementById('create-form').addEventListener('submit', this.onCreate);
        this.client = new TVShowClient();

        this.dataStore.addChangeListener(this.renderExample)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderShows() {
        let resultArea = document.querySelector("result-container");

        const shows = this.dataStore.get("shows");

        if (shows) {
            resultArea.innerHTML = '';
            shows.forEach((show) => {
                const imageResult = document.createElement("div");
                imageResult.classList.add("image-result");

                const image = document.createElement("img");
                image.src = show.imageUrl;
                image.alt = "";

                const overlay = document.createElement("div");
                overlay.classList.add("overlay");

                const overlayTitle = document.createElement("div");
                overlayTitle.classList.add("overlay-title");
                overlayTitle.innerHTML = `<p>${show.name}</p>`;

                const overlayRating = document.createElement("div");
                overlayRating.classList.add("overlay-rating");
                overlayRating.innerHTML = `<p>${show.rating.average}</p>`;

                overlay.appendChild(overlayTitle);
                overlay.appendChild(overlayRating);

                imageResult.appendChild(image);
                imageResult.appendChild(overlay);

                resultArea.appendChild(imageResult);
            });
        } else {
            resultArea.innerHTML = "No Shows Found";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async getPopularShows() {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        this.dataStore.set("shows", null);

        let result = await this.client.getPopular(this.errorHandler);
        this.dataStore.set("shows", result);
        if (result) {
            this.renderShows();
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async getSearchedShow(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let query = document.getElementById("search").value;
        this.dataStore.set("shows", null);

        let result = await this.client.getShow(query, this.errorHandler);
        this.dataStore.set("shows", result);
        if (result) {
            this.renderShows();
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("example", null);

        let name = document.getElementById("create-name-field").value;

        const createdExample = await this.client.createExample(name, this.errorHandler);
        this.dataStore.set("example", createdExample);

        if (createdExample) {
            this.showMessage(`Created ${createdExample.name}!`)
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const homePage = new HomePage();
    homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);
