import { Component, OnInit } from '@angular/core';
import { BookService } from '../../../../services/services';
import { BorrowedBookResponse, PageResponseBorrowedBookResponse } from '../../../../services/models';

@Component({
  selector: 'app-return-books',
  templateUrl: './return-books.component.html',
  styleUrl: './return-books.component.scss'
})
export class ReturnBooksComponent implements OnInit{

  page = 0; size = 5;

  returnedBooks: PageResponseBorrowedBookResponse = {};

  constructor(private bookService: BookService){

  }

  ngOnInit(): void {
    this.findAllBorrowedBoooks();
  }

  findAllBorrowedBoooks():void{
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
    this.findAllBorrowedBoooks();
  }

  gotToPreviousPage():void{
    this.page--;
    this.findAllBorrowedBoooks();
  }
  
  gotToPage(page: number): void{
    this.page = page;
    this.findAllBorrowedBoooks();
  }
  gotToNextPage():void{
    this.page++;
    this.findAllBorrowedBoooks();
  }

  gotToLastPage():void{
    this.page = this.returnedBooks.totalPages as number - 1;
    this.findAllBorrowedBoooks();
  }

  get isLastPage(): boolean{
    return this.page == this.returnedBooks.totalPages as number - 1;
  }

  approveBookReturn(book: BorrowedBookResponse): void{
    
  }

}
