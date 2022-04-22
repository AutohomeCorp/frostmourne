package org.elasticsearch.client;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.common.Strings;

/**
 * @author wangxiaoming
 * @version 1.0
 * @date 2021-05-27
 */
public class RequestExtendConverters {

    public static Request getMappings(GetMappingsRequest getMappingsRequest) throws IOException {
        String[] indices = getMappingsRequest.indices() == null ? Strings.EMPTY_ARRAY : getMappingsRequest.indices();
        String[] types = getMappingsRequest.types() == null ? Strings.EMPTY_ARRAY : getMappingsRequest.types();

        Request request = new Request(HttpGet.METHOD_NAME, RequestConverters.endpoint(indices, "_mapping", types));

        // RequestConverters.Params parameters = new RequestConverters.Params(request);
        // parameters.withMasterTimeout(getMappingsRequest.masterNodeTimeout());
        // parameters.withIndicesOptions(getMappingsRequest.indicesOptions());
        // parameters.withLocal(getMappingsRequest.local());
        return request;
    }

}
