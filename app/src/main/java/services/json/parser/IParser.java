package services.json.parser;

import java.util.List;

/**
 * Created by codebased on 26/07/16.
 */
public interface IParser {
    <T> List<T> deserializeList(String json);

    <T> String serializeList(List<T> items);
}
