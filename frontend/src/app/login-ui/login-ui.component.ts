import {Component, OnInit} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {LoginService} from '../service/login.service';
import {TokenStorage} from '../service/token-storage';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login-ui',
  templateUrl: './login-ui.component.html',
  styleUrls: ['./login-ui.component.css']
})
export class LoginUIComponent implements OnInit {

  private credentials = {username: '', password: ''};
  private return = '';

  constructor(private service: LoginService,
              private token: TokenStorage,
              private route: ActivatedRoute,
              private router: Router,
  ) {
  }

  ngOnInit() {
    this.token.signOut();
    this.route.queryParams.subscribe(params => this.return = params['return']);
  }

  postLogin() {
    this.service.postAuthenticationLogin(this.credentials).subscribe((response: HttpResponse<any>) => {

      console.log(' ---- begin response ----');
      console.log(response);
      console.log(' ---- end response ----');

      this.token.saveToken(response.headers.get('Authorization'), this.credentials.username);
      console.log(' ---- begin token ----');
      console.log(this.token.getToken());
      console.log(' ---- end token ----');


      console.log(' ---- begin pragma ----');
      console.log(response.headers.get('pragma'));
      console.log(' ---- end pragma ----');
      this.router.navigateByUrl(this.return);
    });
  }


}
