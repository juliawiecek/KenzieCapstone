import BaseClass from "../util/baseClass";
import axios from 'axios';

export default class CommentControllerClient extends BaseClass {

    constructor(props = {}) {
        super();
        const methodsToBind = ['clientLoaded', 'createComment', 'getAllComments', 'getTopThreeComments', 'deleteComment', 'updateComment'];
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

    async createComment(commentRequest, errorCallback) {
        try {
            const response = await this.client.post('/api/comments/create', commentRequest);
            return response.data;
        } catch (error) {
            this.handleError("createComment", error, errorCallback);
        }
    }

    async getAllComments(errorCallback) {
        try {
            const response = await this.client.get('/api/comments/all');
            return response.data;
        } catch (error) {
            this.handleError("getAllComments", error, errorCallback);
        }
    }

    async getTopThreeComments(errorCallback) {
        try {
            const response = await this.client.get('/api/comments/top-three');
            return response.data;
        } catch (error) {
            this.handleError("getTopThreeComments", error, errorCallback);
        }
    }

    async deleteComment(commentId, errorCallback) {
        try {
            await this.client.delete(`/api/comments/${commentId}`);//check api address, why not green?
        } catch (error) {
            this.handleError("deleteComment", error, errorCallback);
        }
    }

    async updateComment(commentId, commentRequest, errorCallback) {
        try {
            const response = await this.client.put(`/api/comments/update/${commentId}`, commentRequest);//check api address
            return response.data;
        } catch (error) {
            this.handleError("updateComment", error, errorCallback);
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