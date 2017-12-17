package poc.arkham.common.apiserver.util;

import org.springframework.http.ResponseEntity;
import poc.arkham.common.util.PartialResult;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.PARTIAL_CONTENT;

public class ResponseEnityFactory {

    public static <T> ResponseEntity.BodyBuilder collection(PartialResult<T> results, String resource,
                                                            int maximumRangeSize) {
        return ResponseEntity
                .status(results.getSize() < results.getTotalNumberOfResults() ? PARTIAL_CONTENT : OK)
                .header("Content-Range", results.getOffset() + "-" + results.getLimit() + "/" + results.getSize())
                .header("Accept-Range", resource + " " + maximumRangeSize);
    }
}
