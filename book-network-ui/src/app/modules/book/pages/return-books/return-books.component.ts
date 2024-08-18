import { Component, OnInit } from '@angular/core';
import { BookService } from '../../../../services/services';
import { BorrowedBookResponse, PageResponseBorrowedBookResponse } from '../../../../services/models';

@Component({
  selector: 'app-return-books',
  templateUrl: './return-books.component.html',
  styleUrl: './return-books.component.scss'
})
export class ReturnBooksComponent {

  page = 0; size = 5;
  message = ''; level = 'success';

  returnedBooks: PageResponseBorrowedBookResponse = {};

  constructor(private bookService: BookService){

  }

  ngOnInit(): void {
    this.findAllReturnedBooks();
  }

  findAllReturnedBooks():void{
    this.bookService.findAllReturnedBooks({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (resp: PageResponseBorrowedBookResponse): void =>{
        this.returnedBooks = resp;
      }
    })
  }

  gotToFirstPage():void{
    this.page = 0;
    this.findAllReturnedBooks();
  }

  gotToPreviousPage():void{
    this.page--;
    this.findAllReturnedBooks();
  }
  
  gotToPage(page: number): void{
    this.page = page;
    this.findAllReturnedBooks();
  }
  gotToNextPage():void{
    this.page++;
    this.findAllReturnedBooks();
  }

  gotToLastPage():void{
    this.page = this.returnedBooks.totalPages as number - 1;
    this.findAllReturnedBooks();
  }

  get isLastPage(): boolean{
    return this.page == this.returnedBooks.totalPages as number - 1;
  }

  approveBookReturn(book: BorrowedBookResponse): void{
    if(!book.returned){
      this.level = 'error';
      this.message = 'The book is not yet returned';
      return;
    }
    this.bookService.approveReturnBorrowBook({
      'bookId': book.id as number
    }).subscribe({
      next: (): void => {
        this.level = 'success';
        this.message = 'Book return approved';
        this.findAllReturnedBooks();
      }
    });
  }

}
