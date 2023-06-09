import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, EMPTY } from 'rxjs';

@Component({
  selector: 'app-add-image',
  templateUrl: './add-image.component.html',
  styleUrls: ['./add-image.component.scss'],
})
export class AddImageComponent implements OnInit {
  urlForm: FormGroup;
  isLoading: boolean = false;
  hasError: boolean = false;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    const urlRegex = /^(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&'\(\)\*\+,;=.]+$/;
    this.urlForm = this.formBuilder.group({
      url: [
        '',
        [
          Validators.required,
          Validators.pattern(
            urlRegex
          ),
        ],
      ],
    });
  }
  ngOnInit(): void {}
  analyzeImage() {
    this.isLoading = true;
    const url = this.urlForm.get('url')?.value;
    this.http
      .post('http://localhost:8080/images?url=' + url, {})
      .subscribe((response: any) => {
        this.isLoading = false;
        this.router.navigate(["/image",  response.image.id])
  }, (error) => {
    this.isLoading = false;
    this.hasError = true;
  });
  }
}
