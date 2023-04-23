import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { retry } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HelperService {

  private jwtHelper: JwtHelperService = new JwtHelperService();
  private decodedToken: any;
  constructor() {
    this.decodedToken = this.jwtHelper.decodeToken(localStorage.getItem('token') as string);
  }

  get userId(): number {
    return this.decodedToken.userId;
  }

  get userFullname(): string {
    return this.decodedToken.fullName;
  }
}
