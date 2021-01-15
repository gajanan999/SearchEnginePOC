import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Constants } from './constants/constants';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  getHTTPHeaders(): HttpHeaders {
    let result = new HttpHeaders();
    result = result.set('Content-Type', 'application/json');
    return result;
}

  constructor(private http: HttpClient) { }

  getSearchAllData(keyword:string):Observable<any>{
    const httpHeaders = this.getHTTPHeaders();
    return this.http.get<any>(Constants.URL.HOST_URL+Constants.URL.GET_DATA+keyword,{ headers: httpHeaders,});
  }

  getSearchRelatedData(keyword:string):Observable<any>{
    const httpHeaders = this.getHTTPHeaders();
    return this.http.get<any>(Constants.URL.HOST_URL+Constants.URL.GET_DATA+keyword,{ headers: httpHeaders,});
  }

}
