package com.wrapper.spotify.requests.data.browse;

import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonParser;
import com.wrapper.spotify.exceptions.*;
import com.wrapper.spotify.model_objects.Category;
import com.wrapper.spotify.model_objects.Paging;
import com.wrapper.spotify.requests.AbstractRequest;

import java.io.IOException;

public class CategoriesRequest extends AbstractRequest {

  private CategoriesRequest(final Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder();
  }

  public Paging<Category> get() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return new Category.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("categories").getAsJsonObject());
  }

  public SettableFuture<Paging<Category>> getAsync() throws
          IOException,
          NoContentException,
          BadRequestException,
          UnauthorizedException,
          ForbiddenException,
          NotFoundException,
          TooManyRequestsException,
          InternalServerErrorException,
          BadGatewayException,
          ServiceUnavailableException {
    return getAsync(new Category.JsonUtil().createModelObjectPaging(new JsonParser().parse(getJson()).getAsJsonObject().get("categories").getAsJsonObject()));
  }

  public static final class Builder extends AbstractRequest.Builder<Builder> {
    /*
     * Required. A valid access token from the Spotify Accounts service
     */
    public Builder accessToken(String accessToken) {
      return setHeaderParameter("Authorization", "Bearer " + accessToken);
    }

    /*
     * Optional. A country: an ISO 3166-1 alpha-2 country code. Provide this parameter if you want to narrow the list of returned categories to those relevant to a particular country. If omitted, the returned items will be globally relevant.
     */
    public CategoriesRequest.Builder country(String country) {
      assert (country != null);
      return setParameter("country", country);
    }

    /*
     * Optional. The desired language, consisting of an ISO 639 language code and an ISO 3166-1 alpha-2 country
     * code, joined by an underscore. For example: es_MX, meaning "Spanish (Mexico)". Provide this parameter if
     * you want the category metadata returned in a particular language.
     *
     * Note that, if locale is not supplied, or if the specified language is not available, all strings will be
     * returned in the Spotify default language (American English).
     *
     * The locale parameter, combined with the country parameter, may give odd results if not carefully matched.
     * For example country=SE&locale=de_DE will return a list of categories relevant to Sweden but as German
     * language strings.
     */
    public CategoriesRequest.Builder locale(String locale) {
      assert (locale != null);
      return setParameter("locale", locale);
    }

    /**
     * Optional. The maximum number of categories to return.
     *
     * @param limit Default: 20. Minimum: 1. Maximum: 50
     * @return
     */
    public CategoriesRequest.Builder limit(int limit) {
      assert (limit > 0 && limit <= 50);
      return setParameter("limit", String.valueOf(limit));
    }

    /*
     * Optional. The index of the first item to return.
     * Default: 0 (the first object). Use with limit to get the next set of categories.
     */
    public CategoriesRequest.Builder offset(int offset) {
      assert (offset >= 0);
      return setParameter("offset", String.valueOf(offset));
    }

    @Override
    public CategoriesRequest build() {
      setPath("/v1/browse/categories");
      return new CategoriesRequest(this);
    }
  }
}
