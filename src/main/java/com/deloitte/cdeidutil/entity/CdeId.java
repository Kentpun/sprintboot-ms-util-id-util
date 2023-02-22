package com.deloitte.cdeidutil.entity;

import java.io.Serializable;
import java.util.Arrays;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash(value = "cdeId")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CdeId implements Serializable {

  /*
   * CDE Transaction Types: 1. PY: payment 2. TK: tokenization
   */
  public enum Type {
    PAYMENT("PY"), TOKENIZATION("TK");

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

    public static final Type getByValue(String value) {
      return Arrays.stream(Type.values()).filter(cdeType -> cdeType.type.equals(value)).findFirst()
          .orElse(Type.PAYMENT);
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
