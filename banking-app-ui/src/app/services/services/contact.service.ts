/* tslint:disable */
/* eslint-disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpContext } from '@angular/common/http';
import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';
import { Observable } from 'rxjs';
import { map, filter } from 'rxjs/operators';

import { ContactDto } from '../models/contact-dto';

@Injectable({
  providedIn: 'root',
})
export class ContactService extends BaseService {
  constructor(
    config: ApiConfiguration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * Path part for operation findAll2
   */
  static readonly FindAll2Path = '/contacts/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAll2()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAll2$Response(params?: {
    context?: HttpContext
  }
): Observable<StrictHttpResponse<Array<ContactDto>>> {

    const rb = new RequestBuilder(this.rootUrl, ContactService.FindAll2Path, 'get');
    if (params) {
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: params?.context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<ContactDto>>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `findAll2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAll2(params?: {
    context?: HttpContext
  }
): Observable<Array<ContactDto>> {

    return this.findAll2$Response(params).pipe(
      map((r: StrictHttpResponse<Array<ContactDto>>) => r.body as Array<ContactDto>)
    );
  }

  /**
   * Path part for operation save2
   */
  static readonly Save2Path = '/contacts/';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `save2()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  save2$Response(params: {
    context?: HttpContext
    body: ContactDto
  }
): Observable<StrictHttpResponse<number>> {

    const rb = new RequestBuilder(this.rootUrl, ContactService.Save2Path, 'post');
    if (params) {
      rb.body(params.body, 'application/json');
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: params?.context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: parseFloat(String((r as HttpResponse<any>).body)) }) as StrictHttpResponse<number>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `save2$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  save2(params: {
    context?: HttpContext
    body: ContactDto
  }
): Observable<number> {

    return this.save2$Response(params).pipe(
      map((r: StrictHttpResponse<number>) => r.body as number)
    );
  }

  /**
   * Path part for operation findById2
   */
  static readonly FindById2Path = '/contacts/{contact-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findById2()` instead.
   *
   * This method doesn't expect any request body.
   */
  findById2$Response(params: {
    'contact-id': number;
    context?: HttpContext
  }
): Observable<StrictHttpResponse<ContactDto>> {

    const rb = new RequestBuilder(this.rootUrl, ContactService.FindById2Path, 'get');
    if (params) {
      rb.path('contact-id', params['contact-id'], {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: params?.context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<ContactDto>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `findById2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findById2(params: {
    'contact-id': number;
    context?: HttpContext
  }
): Observable<ContactDto> {

    return this.findById2$Response(params).pipe(
      map((r: StrictHttpResponse<ContactDto>) => r.body as ContactDto)
    );
  }

  /**
   * Path part for operation delete2
   */
  static readonly Delete2Path = '/contacts/{contact-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delete2()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete2$Response(params: {
    'contact-id': number;
    context?: HttpContext
  }
): Observable<StrictHttpResponse<void>> {

    const rb = new RequestBuilder(this.rootUrl, ContactService.Delete2Path, 'delete');
    if (params) {
      rb.path('contact-id', params['contact-id'], {});
    }

    return this.http.request(rb.build({
      responseType: 'text',
      accept: '*/*',
      context: params?.context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `delete2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete2(params: {
    'contact-id': number;
    context?: HttpContext
  }
): Observable<void> {

    return this.delete2$Response(params).pipe(
      map((r: StrictHttpResponse<void>) => r.body as void)
    );
  }

  /**
   * Path part for operation findAllByUserId1
   */
  static readonly FindAllByUserId1Path = '/contacts/users/{user-id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findAllByUserId1()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllByUserId1$Response(params: {
    'user-id': number;
    context?: HttpContext
  }
): Observable<StrictHttpResponse<Array<ContactDto>>> {

    const rb = new RequestBuilder(this.rootUrl, ContactService.FindAllByUserId1Path, 'get');
    if (params) {
      rb.path('user-id', params['user-id'], {});
    }

    return this.http.request(rb.build({
      responseType: 'json',
      accept: 'application/json',
      context: params?.context
    })).pipe(
      filter((r: any) => r instanceof HttpResponse),
      map((r: HttpResponse<any>) => {
        return r as StrictHttpResponse<Array<ContactDto>>;
      })
    );
  }

  /**
   * This method provides access to only to the response body.
   * To access the full response (for headers, for example), `findAllByUserId1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findAllByUserId1(params: {
    'user-id': number;
    context?: HttpContext
  }
): Observable<Array<ContactDto>> {

    return this.findAllByUserId1$Response(params).pipe(
      map((r: StrictHttpResponse<Array<ContactDto>>) => r.body as Array<ContactDto>)
    );
  }

}
