public class LoginController {
    private User user;

    public LoginController() {
        // User default: ganti kalau perlu
        user = new User("admin", "1234");
    }

    public boolean cekLogin(String u, String p) {
        if (u.equals(user.getUsername()) && p.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
