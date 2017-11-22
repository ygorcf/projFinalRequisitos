package com.example.ygor.iluminati.network.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ygor on 15/11/2017.
 */

public class FeedbackResponse extends BaseResponse<List<FeedbackResponse.FeedbackObjectResponse>> {


    public static class FeedbackObjectResponse implements Serializable {

        private String matricula;
        private String comentario;

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getComentario() {
            return comentario;
        }

        public void setComentario(String comentario) {
            this.comentario = comentario;
        }
    }

}
