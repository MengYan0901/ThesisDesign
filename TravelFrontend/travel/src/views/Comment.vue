<template>
    <div>
        <v-row align="center"
               justify="center"
               class="my-2"
               v-show="showAdd">
            <v-col>
                <v-col>
                    <v-btn text
                           block
                           class="my-3"
                           @click="dialogComment = true ">
                        <v-icon large>mdi-credit-card-plus-outline</v-icon>
                        add Your Comments
                    </v-btn>
                </v-col>
            </v-col>
        </v-row>
        <v-row align="center"
               justify="center"
               class="my-2">
            <v-col>
                <v-card class="mx-auto"
                        max-width="344"
                        outlined
                        v-show="!show"
                        v-for="comment in commentList">
                    <v-list-item three-line>
                        <v-list-item-content>
                            <div class="text-overline mb-4">
                                {{query}}
                            </div>
                            <v-list-item-title class="text-h5 mb-1">
                                {{comment.tipComment}}
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                -----by User {{comment.userId}}
                            </v-list-item-subtitle>
                        </v-list-item-content>
                        <v-list-item-action>
                            <v-btn text
                                   @click="editComment(comment)">
                                <v-icon>mdi-credit-card-edit-outline</v-icon>
                            </v-btn>
                            <v-btn text
                                   @click="deleteComment(comment)">
                                <v-icon>mdi-credit-card-remove-outline</v-icon>
                            </v-btn>
                        </v-list-item-action>
                    </v-list-item>
                </v-card>
                <v-card class="mx-auto"
                        max-width="344"
                        outlined
                        v-show="show">
                    <v-list-item three-line>
                        <v-list-item-content>
                            <div class="text-overline mb-4">
                                {{query}}
                            </div>
                            <v-list-item-title class="text-h5 mb-1">
                                There is no comment!
                            </v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-card>
            </v-col>
        </v-row>
        <v-row justify="center">
            <v-dialog v-model="dialogComment"
                      persistent
                      max-width="600px">
                <v-card>
                    <v-card-title>
                        <span class="headline">Add Comment</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field label="TipId*"
                                                  v-model="query"
                                                  required
                                                  disabled></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="TipComment*"
                                                  v-model="selectedComment.tipComment"
                                                  required></v-text-field>
                                </v-col>
                            </v-row>
                        </v-container>
                        <small>* indicates required field</small>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1"
                               text
                               @click="dialogComment = false">
                            Close
                        </v-btn>
                        <v-btn color="blue darken-1"
                               text
                               @click="addComment()">
                            Save
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
        <v-row justify="center">
            <v-dialog v-model="dialogUpdateComment"
                      persistent
                      max-width="600px">
                <v-card>
                    <v-card-title>
                        <span class="headline">Update Comment</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12">
                                    <v-text-field label="TipId*"
                                                  v-model="query"
                                                  required
                                                  disabled></v-text-field>
                                </v-col>
                                <v-col cols="12">
                                    <v-text-field label="TipComment*"
                                                  v-model="updateComment.tipComment"
                                                  required></v-text-field>
                                </v-col>
                            </v-row>
                        </v-container>
                        <small>* indicates required field</small>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1"
                               text
                               @click="dialogUpdateComment = false">
                            Close
                        </v-btn>
                        <v-btn color="blue darken-1"
                               text
                               @click="updatedComment()">
                            Save
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </div>
</template>

<script>
import axios from '../api/axios';

export default {
    name: "Comment.vue",
    props: ['query'],
    data: function () {
        return {
            commentList: [],
            show: false,
            selectedComment: {},
            dialogComment: false,
            dialogUpdateComment: false,
            updateComment: {},
            showAdd: false,

        };
    },
    created: function () {
        if (this.$cookies.get("token") != null) {
            this.showAdd = true;
            this.$store.commit('setuser', this.$cookies.get("token"));
            axios.defaults.headers.Authorization = `Bearer ${this.$store.state.user}`;
        };
        this.searchCommentByTipId();
    },
    methods: {
        searchCommentByTipId() {
            if (this.$cookies.get("token") == null) {
                window.alert("You have to log in first!");
            } else {
                this.$api.comment.searchCommentByTipId(this.query).then((res) => {
                    if (res.data == 0) {
                        console.log(res.data);
                        this.show = true;
                    }
                    else {
                        console.log(res.data);
                        this.commentList = res.data;
                    }
                });
            }
        },
        closeComment() {
            this.dialogComment = false;
            this.selectedComment = {};
        },
        addComment() {
            this.selectedComment.userId = this.$cookies.get("userId");
            this.selectedComment.tipId = this.query;
            console.log(this.selectedComment);
            this.$api.comment.addComment(this.selectedComment).then(res => {
                console.log(res);
                this.dialogComment = false;
                this.selectedComment = {};
                this.searchCommentByTipId();
            });
        },
        editComment(comment) {
            this.updateComment.commentId = comment.commentId;
            this.updateComment.tipComment = comment.tipComment;
            if (comment.userId != this.$cookies.get("userId")) {
                window.alert("You have no permission to edit this comment!")
            }
            else {
                this.dialogUpdateComment = true;
            }

        },
        updatedComment() {
            this.updateComment.userId = this.$cookies.get("userId");
            this.updateComment.tipId = this.query;
            console.log(this.updatedComment);
            this.$api.comment.updateComment(this.updateComment).then(res => {
                this.dialogUpdateComment = false;
                this.searchCommentByTipId();
            });
        },
        deleteComment(comment) {
            this.updateComment.commentId = comment.commentId;
            if (comment.userId != this.$cookies.get("userId")) {
                window.alert("You have no permission to delete this comment!")
            }
            else {
                this.$api.comment.deleteComment(this.updateComment.commentId).then(res => {
                    if (res.data == 1) {
                        window.alert("You have deleted the comment successfully")
                        this.searchCommentByTipId();
                    } else {
                        window.alert("You have NOT deleted the comment successfully")
                    }
                });
            }
        },

    },
};
</script>

<style scope
</style>