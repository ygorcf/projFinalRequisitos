package com.example.ygor.iluminati.network.responses;

/**
 * Created by notoriun on 21/11/17.
 */

public class LoginResponse extends BaseResponse<LoginResponse.LoginObjectResponse> {

    public static class LoginObjectResponse {

        private boolean logged;
        private String matricula;
        private String userType;

        public boolean isLogged() {
            return logged;
        }

        public void setLogged(boolean logged) {
            this.logged = logged;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

    }

}
