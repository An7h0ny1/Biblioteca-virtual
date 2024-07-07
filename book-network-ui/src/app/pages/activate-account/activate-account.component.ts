import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';
//asdasdasd

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent {

  message: string = '';
  isOkay: boolean = true;
  submitted: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ){}

  onCodeCompleted(token: string):void{
      this.confirmAccount(token);
  }

  redirectToLogin(): void{
    this.router.navigate(['login'])
  }

  private confirmAccount(token: string): void{
    this.authService.confirm({
      token
    }).subscribe({
      next:(): void =>{
        this.message = 'Account activated successfully\n';
        this.submitted = true;
      },
      error: (): void  =>{
        this.message = 'Token ha expirado o es invalido';
        this.submitted = true;
        this.isOkay = false;
      }
    });
  }
}
