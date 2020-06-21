package com.boycoder.kotlinjetpackinaction.entity

import java.util.*

class User {

    companion object {
        val CACHE_RESPONSE = "{\"login\":\"JakeWharton\",\"id\":66577,\"node_id\":\"MDQ6VXNlcjY2NTc3\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/66577?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/JakeWharton\",\"html_url\":\"https://github.com/JakeWharton\",\"followers_url\":\"https://api.github.com/users/JakeWharton/followers\",\"following_url\":\"https://api.github.com/users/JakeWharton/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/JakeWharton/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/JakeWharton/subscriptions\",\"organizations_url\":\"https://api.github.com/users/JakeWharton/orgs\",\"repos_url\":\"https://api.github.com/users/JakeWharton/repos\",\"events_url\":\"https://api.github.com/users/JakeWharton/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/JakeWharton/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Jake Wharton\",\"company\":\"Square\",\"blog\":\"https://jakewharton.com\",\"location\":\"Pittsburgh, PA, USA\",\"email\":null,\"hireable\":null,\"bio\":null,\"twitter_username\":null,\"public_repos\":104,\"public_gists\":54,\"followers\":57849,\"following\":12,\"created_at\":\"2009-03-24T16:09:53Z\",\"updated_at\":\"2020-05-28T00:07:20Z\"}"
    }

    private var id: String? = null
    private var login: String? = null
    private var avatar_url: String? = null
    private var name: String? = null
    private var company: String? = null
    private var blog: String? = null
    private var lastRefresh: Date? = null

    constructor() {}
    constructor(id: String, login: String?, avatar_url: String?, name: String?, company: String?, blog: String?, lastRefresh: Date?) {
        this.id = id
        this.login = login
        this.avatar_url = avatar_url
        this.name = name
        this.company = company
        this.blog = blog
        this.lastRefresh = lastRefresh
    }

    fun getId(): String? { return id }
    fun getAvatar_url(): String? { return avatar_url }
    fun getLastRefresh(): Date? { return lastRefresh }
    fun getLogin(): String? { return login }
    fun getName(): String? { return name }
    fun getCompany(): String? { return company }
    fun getBlog(): String? { return blog }

    fun setId(id: String?) { this.id = id }
    fun setAvatar_url(avatar_url: String?) { this.avatar_url = avatar_url }
    fun setLastRefresh(lastRefresh: Date?) { this.lastRefresh = lastRefresh }
    fun setLogin(login: String?) { this.login = login }
    fun setName(name: String?) { this.name = name }
    fun setCompany(company: String?) { this.company = company }
    fun setBlog(blog: String?) { this.blog = blog }
}