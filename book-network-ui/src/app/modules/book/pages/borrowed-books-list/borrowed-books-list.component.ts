import { Component, OnInit } from '@angular/core';
import { BorrowedBookResponse, FeedBackRequest, PageResponseBorrowedBookResponse } from '../../../../services/models';
import { BookService, FeedBackService } from '../../../../services/services';

@Component({
  selector: 'app-borrowed-books-list',
  templateUrl: './borrowed-books-list.component.html',
  styleUrl: './borrowed-books-list.component.scss'
})
export class BorrowedBooksListComponent implements OnInit{

  page = 0; size = 5;
  selectedBook: BorrowedBookResponse | undefined = undefined;
  feedbackRequest: FeedBackRequest = {bookId: 0, comment: '', note: 0};
  
  borrowedBooks: PageResponseBorrowedBookResponse = {};

  constructor(private bookService: BookService, private feedbackService: FeedBackService){

  }

  ngOnInit(): void {
    this.findAllBorrowedBoooks();
  }

  returnBorrowedBook(book: BorrowedBookResponse): void{
    this.selectedBook = book;
    this.feedbackRequest.bookId = book.id as number;
  }

  findAllBorrowedBoooks():void{
    this.bookService.findAllBorrowedBooks({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (resp: PageResponseBorrowedBookResponse): void =>{
        console.log("holaa" , resp);  // Verificar la respuesta
        this.borrowedBooks = resp;
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
    this.page = this.borrowedBooks.totalPages as number - 1;
    this.findAllBorrowedBoooks();
  }

  get isLastPage(): boolean{
    return this.page == this.borrowedBooks.totalPages as number - 1;
  }

  returnBook(withFeedback: boolean) {
    console.log("Returning book with ID:", this.selectedBook?.id);
    this.bookService.returnBorrowBook({ 'bookId': this.selectedBook?.id as number }).subscribe({
        next: (): void => {
            if (withFeedback) {
                this.giveFeedBack();
            }
            this.selectedBook = undefined;
            this.findAllBorrowedBoooks();
        }
    });
  }



  private giveFeedBack(): void {
    this.feedbackService.saveFeedback({
      body: this.feedbackRequest
    }).subscribe({
      next: (): void =>{

      }
    })
  }
}
