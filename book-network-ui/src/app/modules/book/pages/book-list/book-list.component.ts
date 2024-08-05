import { Component, OnInit } from '@angular/core';
import { BookService } from '../../../../services/services';
import { Router } from '@angular/router';
import { PageResponseBookResponse } from '../../../../services/models';


@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.scss'
})
export class BookListComponent implements OnInit {
  bookResponse: PageResponseBookResponse = {};
  page = 0; size = 5;

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
    }).subscribe({
      next: (books: PageResponseBookResponse): void => {
        console.log('Books fetched successfully:', books);
        this.bookResponse = books;
        console.log('Book response assigned to component:', this.bookResponse);
      },
      error: (err) => {
        console.error('Error fetching books:', err);
      }
    });
  }
  
}
