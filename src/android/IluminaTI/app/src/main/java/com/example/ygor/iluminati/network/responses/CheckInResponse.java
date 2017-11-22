package com.example.ygor.iluminati.network.responses;

/**
 * Created by Ygor on 14/11/2017.
 */

public class CheckInResponse extends BaseResponse<CheckInResponse.CheckInQrCodeResponse> {

    public class CheckInQrCodeResponse {

        private boolean checkin;
        private String message;
        private PalestraResponse palestra;

        public boolean isCheckin() {
            return checkin;
        }

        public void setCheckin(boolean checkin) {
            this.checkin = checkin;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public PalestraResponse getPalestra() {
            return palestra;
        }

        public void setPalestra(PalestraResponse palestra) {
            this.palestra = palestra;
        }

    }
}
