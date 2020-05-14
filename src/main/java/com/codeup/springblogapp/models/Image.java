package com.codeup.springblogapp.models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String path;

    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;

    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;

    public Image() {}

    public Image(String path, Post post, User user) {
        this.path = path;
        this.post = post;
        this.user = user;
    }

    public Image(long id, String path, Post post, User user) {
        this.id = id;
        this.path = path;
        this.post = post;
        this.user = user;
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
