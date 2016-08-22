package services.json;

public interface DataCallback<T> {
    void onSuccess(T result);

    void onFailure(String error);
}
