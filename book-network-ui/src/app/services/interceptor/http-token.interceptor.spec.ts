import { TestBed } from '@angular/core/testing';
import { HttpRequest, HttpHandler } from '@angular/common/http';
import { HttpTokenInterceptor } from './http-token.interceptor';
import { TokenService } from '../token/token.service';

describe('HttpTokenInterceptor', () => {
  let interceptor: HttpTokenInterceptor;
  let tokenServiceSpy: jasmine.SpyObj<TokenService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('TokenService', ['token']);

    TestBed.configureTestingModule({
      providers: [
        HttpTokenInterceptor,
        { provide: TokenService, useValue: spy }
      ]
    });

    interceptor = TestBed.inject(HttpTokenInterceptor);
    tokenServiceSpy = TestBed.inject(TokenService) as jasmine.SpyObj<TokenService>;
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });
});