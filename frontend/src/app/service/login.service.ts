import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TokenStorage} from './token-storage';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient,
              private token: TokenStorage) {
  }

  postAuthenticationLogin(credentials: any) {
    return this.http.post('//localhost:8080/login', JSON.stringify({
      'username': credentials.username,
      'password': credentials.password
    }), {observe: 'response'});
  }
}
