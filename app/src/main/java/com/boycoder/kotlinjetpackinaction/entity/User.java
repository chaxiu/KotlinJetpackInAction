package com.boycoder.kotlinjetpackinaction.entity;


import androidx.annotation.NonNull;

import java.util.Date;


public class User {

    public static final String CACHE_RESPONSE = "{\"login\":\"JakeWharton\",\"id\":66577,\"node_id\":\"MDQ6VXNlcjY2NTc3\",\"avatar_url\":\"https://avatars0.githubusercontent.com/u/66577?v=4\",\"gravatar_id\":\"\",\"url\":\"https://api.github.com/users/JakeWharton\",\"html_url\":\"https://github.com/JakeWharton\",\"followers_url\":\"https://api.github.com/users/JakeWharton/followers\",\"following_url\":\"https://api.github.com/users/JakeWharton/following{/other_user}\",\"gists_url\":\"https://api.github.com/users/JakeWharton/gists{/gist_id}\",\"starred_url\":\"https://api.github.com/users/JakeWharton/starred{/owner}{/repo}\",\"subscriptions_url\":\"https://api.github.com/users/JakeWharton/subscriptions\",\"organizations_url\":\"https://api.github.com/users/JakeWharton/orgs\",\"repos_url\":\"https://api.github.com/users/JakeWharton/repos\",\"events_url\":\"https://api.github.com/users/JakeWharton/events{/privacy}\",\"received_events_url\":\"https://api.github.com/users/JakeWharton/received_events\",\"type\":\"User\",\"site_admin\":false,\"name\":\"Jake Wharton\",\"company\":\"Square\",\"blog\":\"https://jakewharton.com\",\"location\":\"Pittsburgh, PA, USA\",\"email\":null,\"hireable\":null,\"bio\":null,\"twitter_username\":null,\"public_repos\":104,\"public_gists\":54,\"followers\":57849,\"following\":12,\"created_at\":\"2009-03-24T16:09:53Z\",\"updated_at\":\"2020-05-28T00:07:20Z\"}";

    private String id;
    private String login;
    private String avatar_url;
    private String name;
    private String company;
    private String blog;
    private Date lastRefresh;


    public User() { }

    public User(@NonNull String id, String login, String avatar_url, String name, String company, String blog, Date lastRefresh) {
        this.id = id;
        this.login = login;
        this.avatar_url = avatar_url;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.lastRefresh = lastRefresh;
    }

    public String getId() { return id; }
    public String getAvatar_url() { return avatar_url; }
    public Date getLastRefresh() { return lastRefresh; }
    public String getLogin() { return login; }
    public String getName() { return name; }
    public String getCompany() { return company; }
    public String getBlog() { return blog; }

    public void setId(String id) { this.id = id; }
    public void setAvatar_url(String avatar_url) { this.avatar_url = avatar_url; }
    public void setLastRefresh(Date lastRefresh) { this.lastRefresh = lastRefresh; }
    public void setLogin(String login) { this.login = login; }
    public void setName(String name) { this.name = name; }
    public void setCompany(String company) { this.company = company; }
    public void setBlog(String blog) { this.blog = blog; }
}
