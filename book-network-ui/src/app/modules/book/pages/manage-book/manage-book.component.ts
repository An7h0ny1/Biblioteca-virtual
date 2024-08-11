import { Component, OnInit } from '@angular/core';
import { BookRequest, BookResponse } from '../../../../services/models';
import { BookService } from '../../../../services/services';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-manage-book',
  templateUrl: './manage-book.component.html',
  styleUrl: './manage-book.component.scss'
})
export class ManageBookComponent implements OnInit{
  bookRequest: BookRequest = {author: '', isbn: '', synopsis: '', title: ''};
  errorMsg: Array<string> = [];
  selectedPicture: string | undefined;
  selectedBookCover: any;

  constructor(private bookService: BookService, private router: Router, private activatedRoute: ActivatedRoute){

  }
  ngOnInit(): void {
    const bookId = this.activatedRoute.snapshot.params['bookId'];
    if(bookId){
      this.bookService.getBookById({
        'id': bookId
      }).subscribe({
        next: (book: BookResponse):void =>{
          this.bookRequest = {
            id: book.id,
            title: book.title as string,
            author: book.author as string,
            isbn: book.isbn as string,
            synopsis: book.synopsis as string,
            shareable: book.shareable
          }
          if(book.bookCover){
            this.selectedPicture = 'data:imgae/jpg;base64,' + book.bookCover; 
          }
        }
      });
    }
  }

  onFileSelected(event: any):void{
    this.selectedBookCover = event.target.files[0];
    console.log(this.selectedBookCover);
    if(this.selectedBookCover){
      const reader = new FileReader();
      reader.onload = (): void => {
        this.selectedPicture = reader.result as string;
      }
      reader.readAsDataURL(this.selectedBookCover);
    }
  }

  saveBook(): void{
    this.bookService.addBook({
      body: this.bookRequest
    }).subscribe({
      next: (bookId: number): void =>{
        this.bookService.uploadBookCoverPicture({
          'bookId': bookId,
          body: {
            file: this.selectedBookCover
          }
        }).subscribe({
          next: ():void =>{
            this.router.navigate(['/books/my-books']);
          }
        })
      },
      error: (err): void =>{
        this.errorMsg = err.error.validationErrors;
      }
    });
  }
}
