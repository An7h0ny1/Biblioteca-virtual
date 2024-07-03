import { Component } from '@angular/core';
import { AuthenticationRequest } from '../../services/models/authentication-request';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
import { AuthenticationResponse } from '../../services/models';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  login():void {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next: (res: AuthenticationResponse): void =>{
        this.router.navigate(['books']);
      },
      error: (err): void =>{
        console.log(err);
      }
    })
  }

  register(): void {
    this.router.navigate(['register']);
  }
}
