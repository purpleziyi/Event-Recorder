package cc.ziyi.service;

import cc.ziyi.pojo.User;

public interface UserService {
    // find users based on username
    User findByUserName(String username);

    // register
    void register(String username, String password);

    // update
    void update(User user);

    // update profile-picture
    void updateAvatar(String avatarUrl);

    // update password
    void updatePwd(String newPwd);
}
