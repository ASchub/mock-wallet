package com.example.mockwallet.core.exception;

public final class WalletException extends RuntimeException {
  private final BaseType type;

  private WalletException(BaseType type, String message, Throwable cause) {
    super(message, cause);
    this.type = type;
  }

  private WalletException(BaseType type, String message) {
    super(message);
    this.type = type;
  }

  public static WalletException badRequest(String message) {
    return new WalletException(BaseType.BAD_REQUEST, message);
  }

  public static WalletException internalError(String message) {
    return new WalletException(BaseType.INTERNAL_ERROR, message);
  }

  public static WalletException internalError() {
    return new WalletException(BaseType.INTERNAL_ERROR, lazyError());
  }

  public static WalletException badRequest() {
    return new WalletException(BaseType.BAD_REQUEST, lazyError());
  }

  public static WalletException notFound() {
    return new WalletException(BaseType.NOT_FOUND, lazyError());
  }

  public static WalletException notFound(String message) {
    return new WalletException(BaseType.NOT_FOUND, message);
  }

  public BaseType getType() {
    return type;
  }

  public static WalletException badRequest(String message, Throwable cause) {
    return new WalletException(BaseType.BAD_REQUEST, message, cause);
  }

  private static String lazyError() {
    return "Something went wrong but Alex was to lazy to write a proper error message :)";
  }

  public enum BaseType {
    INTERNAL_ERROR(500),
    NOT_FOUND(404),
    BAD_REQUEST(400);

    private final int httpStatus;

    BaseType(int httpStatus) {
      this.httpStatus = httpStatus;
    }
    public int getHttpStatus() {
      return httpStatus;
    }


  }
}
