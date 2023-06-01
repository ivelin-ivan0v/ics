import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-image',
  templateUrl: './add-image.component.html',
  styleUrls: ['./add-image.component.scss'],
})
export class AddImageComponent implements OnInit {
  urlForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {
    this.urlForm = this.formBuilder.group({
      url: [
        '',
        [
          Validators.required,
          Validators.pattern(
            '(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?'
          ),
        ],
      ],
    });
  }
  ngOnInit(): void {}
  analyzeImage() {
    this.router.navigate(['/image', 1]);
    // const url = this.urlForm.get('url')?.value;
    // this.http
    //   .post('http://localhost:8080/images', { url })
    //   .subscribe((response) => {
    //     console.log(response);
    //   });
  }
}
