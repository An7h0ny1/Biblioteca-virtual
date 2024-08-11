import { Component, OnInit } from '@angular/core';
import { BookService } from '../../../../services/services';
import { Router } from '@angular/router';
import { BookResponse, PageResponseBookResponse } from '../../../../services/models';


@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrl: './my-books.component.scss'
})
export class MyBooksComponent  implements OnInit{

  bookResponse: PageResponseBookResponse = {};
  page = 0; size = 2; 
  pages: any = [];

  constructor(
    private bookService: BookService,
    private router: Router
  ){

  }

  ngOnInit(): void {
    this.findAllBooks();
  }

  private findAllBooks(): void {
    console.log('Fetching books with params:', { page: this.page, size: this.size });
    this.bookService.findAllBooksByOwner({
      page: this.page,
      size: this.size
    }) .subscribe({
      next: (books) => {
        this.bookResponse = books;
      }
    });
  }

  gotToFirstPage():void{
    this.page = 0;
    this.findAllBooks();
  }

  gotToPreviousPage():void{
    this.page--;
    this.findAllBooks();
  }
  
  gotToPage(page: number): void{
    this.page = page;
    this.findAllBooks();
  }
  gotToNextPage():void{
    this.page++;
    this.findAllBooks();
  }

  gotToLastPage():void{
    this.page = this.bookResponse.totalPages as number - 1;
    this.findAllBooks();
  }

  get isLastPage(): boolean{
    return this.page == this.bookResponse.totalPages as number - 1;
  }

  archiveBook(book: BookResponse): void{

  }

  shareBook(book: BookResponse): void{
    
  }

  editBook(book: BookResponse): void{
    
  }
}
