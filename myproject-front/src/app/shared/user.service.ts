import {Injectable} from '@angular/core';
import {Http, Headers, RequestOptions} from '@angular/http';
import {Observable} from "rxjs";
import {environment} from "environments/environment";

@Injectable()
export class UserService {

  private _backendURL:any;


  constructor(private _http:Http) {
    this._backendURL = {};

    // build backend base url
    let baseUrl = `${environment.backend.protocol}://${environment.backend.host}`;
    if (environment.backend.port) {
      baseUrl += `:${environment.backend.port}`;
    }

    // build all backend urls
    Object.keys(environment.backend.endpoints).forEach(k => this._backendURL[k] = `${baseUrl}${environment.backend.endpoints[k]}`);
  }

  findAll():Observable<any[]> {
    return this._http.get(this._backendURL.allUsers)
      .map(res=>res.json());
  }

}

