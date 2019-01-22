import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {TokenStorage} from './token-storage';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private token: TokenStorage) {
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (this.token.getToken() != null) {
      return true;
    } else {
      this.router.navigate(['/login'], {
       queryParams: {
          return: state.url
        }
      });
      return false;
    }
  }
}
