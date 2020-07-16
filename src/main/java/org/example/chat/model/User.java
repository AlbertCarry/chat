package org.example.chat.model;

import javax.persistence.*;

@Entity
@Table(name = "user_spau")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login")
    private String login;
    @Column(name = "hash_password")
    private String hashPassword;
    @Column(name = "role")
    private String role;
    @Column(name = "state")
    private String state;
    @Column(name = "email")
    private String email;

    public User() {
    }
    private User(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.login = builder.login;
        this.hashPassword = builder.hashPassword;
        this.role = builder.role;
        this.state = builder.state;
        this.email = builder.email;
    }

    public static Builder builder(){
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String login;
        private String hashPassword;
        private String role;
        private String state;
        private String email;

        public Builder() {
        }

      public String getFirstName() {
          return firstName;
      }

      public Builder setFirstName(String firstName) {
          this.firstName = firstName;
          return this;
      }

      public String getLastName() {
          return lastName;
      }

      public Builder setLastName(String lastName) {
          this.lastName = lastName;
          return this;
      }

      public String getLogin() {
          return login;
      }

      public Builder setLogin(String login) {
          this.login = login;
          return this;
      }

      public String getHashPassword() {
          return hashPassword;
      }

      public Builder setHashPassword(String hashPassword) {
          this.hashPassword = hashPassword;
          return this;
      }

      public String getRole() {
          return role;
      }

      public Builder setRole(String role) {
          this.role = role;
          return this;
      }

      public String getState() {
          return state;
      }

      public Builder setState(String state) {
          this.state = state;
          return this;
      }

        public String getEmail() {
            return email;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build(){ return new User(this);}
    }
}
