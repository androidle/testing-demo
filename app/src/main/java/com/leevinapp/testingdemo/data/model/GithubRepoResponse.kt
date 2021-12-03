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
            archiveUrl = this.archive_url,
            archived = this.archived,
            assigneesUrl = this.assignees_url,
            blobsUrl = this.blobs_url,
            branchesUrl = this.branches_url
        )
    }
}
