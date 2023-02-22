package com.deloitte.cdeidutil.entity;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import lombok.*;

@RedisHash(value="cdeId")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CdeId implements Serializable {

    /*
        CDE Transaction Types:
        1. PY: payment
        2. TK: tokenization
     */
    public enum Type {
        payment("PY"), tokenization("TK");

        private final String type;

        /**
         * @param text
         */
        Type(final String text) {
            this.type = text;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    private Type type;

    private String dateTimeString;

    @Id
    private String uuid;

    @TimeToLive
    private Long expiration;

    public CdeId(Type type, String dateTimeString, Long timeToLive) {
        this.type = type;
        this.dateTimeString = dateTimeString;
        this.expiration = timeToLive;
    }

    public CdeId(Type type, String dateTimeString) {
        this.type = type;
        this.dateTimeString = dateTimeString;
    }

}
