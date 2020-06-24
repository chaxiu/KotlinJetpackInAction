package com.boycoder.kotlinjetpackinaction.entity

import java.util.*

data class User(
        var id: String? = null,
        var login: String? = null,
        var avatar_url: String? = null,
        var name: String? = null,
        var company: String? = null,
        var blog: String? = null,
        var lastRefresh: Date? = null
) {
    companion object {
        val CACHE_RESPONSE = "{\"login\":\"JakeWharton\",\"id\":66577,\"node_id\":\"MDQ6VXNlcjY2NTc3\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/66577?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/JakeWharton\",\"html_url\":\"https://github.com/JakeWharton\",\"followers_url\":\"https://api.github.com/users/JakeWharton/followers\",\"following_url\":\"https://api.github.com/users/JakeWharton/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/JakeWharton/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/JakeWharton/subscriptions\",\"organizations_url\":\"https://api.github.com/users/JakeWharton/orgs\",\"repos_url\":\"https://api.github.com/users/JakeWharton/repos\",\"events_url\":\"https://api.github.com/users/JakeWharton/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/JakeWharton/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Jake Wharton\",\"company\":\"Square\",\"blog\":\"https://jakewharton.com\",\"location\":\"Pittsburgh, PA, USA\",\"email\":null,\"hireable\":null,\"bio\":null,\"twitter_username\":null,\"public_repos\":104,\"public_gists\":54,\"followers\":57849,\"following\":12,\"created_at\":\"2009-03-24T16:09:53Z\",\"updated_at\":\"2020-05-28T00:07:20Z\"}"
    }
}