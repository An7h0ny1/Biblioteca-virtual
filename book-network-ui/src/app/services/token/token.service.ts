import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  set token(token: string) {
    console.log('Setting token:', token); // Log para verificar el token que se está almacenando
    localStorage.setItem('token', token);
  }

  get token() {
    const token = localStorage.getItem('token');
    console.log('Getting token:', token); // Log para verificar el token que se está recuperando
    return token as string;
  }

  isTokenNotValid(): boolean{
    return !this.isTokenValid();
  }

  private isTokenValid(): boolean {
    const token = this.token;
    if (!token) {
      return false;
    }
    const jwtHelper = new JwtHelperService();
    const isTokenExpired = jwtHelper.isTokenExpired(token);
    if(isTokenExpired){
      localStorage.clear();
      return false;
    }
    return true;
  }
}
