import { Component, OnInit } from '@angular/core';
import { BookService } from '../../../../services/services';
import { Router } from '@angular/router';
import { BookResponse, PageResponseBookResponse } from '../../../../services/models';


@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.scss'
})
export class BookListComponent implements OnInit {
  bookResponse: PageResponseBookResponse = {};
  page = 0; size = 2; 
  message = ''; level = 'success';
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
    this.bookService.findAllBooks({
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

  borrowBook(book: BookResponse): void{
    this.message = '';
    this.bookService.borrowBook({
      'id': book.id as number
    }).subscribe({
      next: (): void => {
        this.level = 'success';
        this.message = 'Book successfully added to your list';
      },
      error: (err): void =>{
        console.log(err);
        this.level = 'error';
        this.message = err.error.error;
      }
    });
  }
}
