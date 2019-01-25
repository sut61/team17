import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';

const TOKEN_KEY = 'InsuranceAuthToken';
const EMPLOYEE_USERNAME = 'username';
@Injectable({
  providedIn: 'root'
})
export class TokenStorage {
  private header = new HttpHeaders();

  constructor() {
  }

  public getHeader(): string {
    return sessionStorage.getItem('InsuranceAuthToken');
  }

  public signOut() {
    window.sessionStorage.clear();

  }

  public saveToken(token: string, username: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
    window.sessionStorage.setItem(EMPLOYEE_USERNAME, username);

  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

}
