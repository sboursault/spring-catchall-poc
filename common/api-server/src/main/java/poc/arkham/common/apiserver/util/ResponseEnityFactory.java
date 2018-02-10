package poc.arkham.common.apiserver.util;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import poc.arkham.common.util.PartialResult;
import poc.arkham.common.web.resource.ResultResource;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.hateoas.Link.*;
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


    public static <T> ResponseEntity<ResultResource<T>> collection(Pageable p, Page<T> results, String resource, int maximumRangeSize) {


        ResultResource<T> body = new ResultResource(results.getContent());

        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Assert.state(servletRequest != null, "Could not find current HttpServletRequest");
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(servletRequest.getRequestURL().toString());

        if (results.hasPrevious()) {
            body.withLink(new Link(uriComponentsBuilder.replaceQueryParam("page", "0").toUriString()).withRel(REL_FIRST))
                .withLink(new Link(uriComponentsBuilder.replaceQueryParam("page", p.previousOrFirst().getPageNumber()).toUriString()).withRel(REL_PREVIOUS));
        }
        if (results.hasNext()) {
            body.withLink(new Link(uriComponentsBuilder.replaceQueryParam("page", p.next().getPageNumber()).toUriString()).withRel(REL_NEXT))
                    .withLink(new Link(uriComponentsBuilder.replaceQueryParam("page", results.getTotalPages()).toUriString()).withRel(REL_LAST));
        }

        return ResponseEntity
                .status(results.getSize() <= results.getTotalElements() ? OK : PARTIAL_CONTENT)
                .header("Content-Range", p.getOffset() + "-" + (p.getOffset() + results.getSize() - 1) + "/" + results.getSize())
                .header("Accept-Range", resource + " " + maximumRangeSize)
                .body(body);
    }
}
