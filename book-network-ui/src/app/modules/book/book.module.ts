import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BookRoutingModule } from './book-routing.module';
import { MainComponent } from './pages/main/main.component';
import { BookListComponent } from './pages/book-list/book-list.component';
import { BookCardComponent } from "./components/book-card/book-card.component";
import { RatingComponent } from './components/rating/rating.component';
import { MenuComponent } from "./components/menu/menu.component";
import { MyBooksComponent } from './pages/my-books/my-books.component';
import { FormsModule } from '@angular/forms';
import { ManageBookComponent } from './pages/manage-book/manage-book.component';
import { BorrowedBooksListComponent } from './pages/borrowed-books-list/borrowed-books-list.component';
import { ReturnBooksComponent } from './pages/return-books/return-books.component';


@NgModule({
  declarations: [
    MainComponent,
    BookListComponent,
    MenuComponent,
    MyBooksComponent,
    ManageBookComponent,
    BorrowedBooksListComponent,
    ReturnBooksComponent
  ],
  imports: [
    CommonModule,
    BookRoutingModule,
    BookCardComponent,
    RatingComponent,
    FormsModule
]
})
export class BookModule { }
