package com.leevinapp.testingdemo.data.model

import com.leevinapp.testingdemo.repository.model.GithubRepo

data class GithubRepoResponse(
    val archive_url: String?,
    val archived: Boolean?,
    val assignees_url: String?,
    val blobs_url: String?,
    val branches_url: String?
) {
    fun toModel(): GithubRepo {
        return GithubRepo(
            archive_url = this.archive_url,
            archived = this.archived,
            assignees_url = this.assignees_url,
            blobs_url = this.blobs_url,
            branches_url = this.branches_url
        )
    }
}
