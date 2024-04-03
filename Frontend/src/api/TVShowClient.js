import BaseClass from "../util/baseClass";
import axios from 'axios';

export default class TVShowClient extends BaseClass {

    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'getPopular', 'getShow', 'getShowInfo', 'getShowEpisodes', 'getShowImages', 'getShowSeasons'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady();
        }
    }

    async getPopular(errorCallback) {
        try {
            const response = await this.client.get(`/api/shows/popular`);
            return response.data;
        } catch (error) {
            this.handleError("getPopular", error, errorCallback);
        }
    }

    async getShow(query, errorCallback) {
        try {
            const response = await this.client.get(`/api/shows/${query}`);
            return response.data;
        } catch (error) {
            this.handleError("getShow", error, errorCallback);
        }
    }

    async getShowInfo(id, errorCallback) {
        try {
            const response = await this.client.get(`/api/shows/info/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getShowInfo", error, errorCallback);
        }
    }

    async getShowEpisodes(id, errorCallback) {
        try {
            const response = await this.client.get(`/api/shows/episodes/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getShowEpisodes", error, errorCallback);
        }
    }

    async getShowImages(id, errorCallback) {
        try {
            const response = await this.client.get(`/api/shows/images/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getShowImages", error, errorCallback);
        }
    }

    async getShowSeasons(id, errorCallback) {
        try {
            const response = await this.client.get(`/api/shows/seasons/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("getShowSeasons", error, errorCallback);
        }
    }

    handleError(method, error, errorCallback) {
            console.error(`${method} failed - ${error}`);
            if (error.response && error.response.data && error.response.data.message) {
                console.error(error.response.data.message);
            }
            if (errorCallback) {
                errorCallback(`${method} failed - ${error}`);
            }
        }
}