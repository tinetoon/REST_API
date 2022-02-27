package com.imgur.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Игнорируем лишние поля из строки JSON
public class DataUser {

    @JsonProperty("account_url")
    private String accountUrl;
    @JsonProperty("email")
    private String email;

    @JsonProperty("public_images")
    public Boolean publicImages;
    @JsonProperty("album_privacy")
    public String albumPrivacy;

    @JsonProperty("accepted_gallery_terms")
    public Boolean acceptedGalleryTerms;
    @JsonProperty("active_emails")
    public List<Object> activeEmails = new ArrayList<Object>();
    @JsonProperty("messaging_enabled")
    public Boolean messagingEnabled;
    @JsonProperty("comment_replies")
    public Boolean commentReplies;
    @JsonProperty("blocked_users")
    public List<Object> blockedUsers = new ArrayList<Object>();
    @JsonProperty("show_mature")
    public Boolean showMature;
    @JsonProperty("newsletter_subscribed")
    public Boolean newsletterSubscribed;
    @JsonProperty("first_party")
    public Boolean firstParty;

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("url")
    private String url;
    @JsonProperty("bio")
    private Object bio;
    @JsonProperty("avatar")
    private String avatar;
    @JsonProperty("avatar_name")
    private String avatarName;
    @JsonProperty("cover")
    private String cover;
    @JsonProperty("cover_name")
    private String coverName;
    @JsonProperty("reputation")
    private Integer reputation;
    @JsonProperty("reputation_name")
    private String reputationName;
    @JsonProperty("created")
    private Integer created;
    @JsonProperty("pro_expiration")
    private Boolean proExpiration;
    @JsonProperty("user_follow")
    private UserFollow userFollow;
    @JsonProperty("is_blocked")
    private Boolean isBlocked;
}
