import { Injectable } from '@angular/core';

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
}
