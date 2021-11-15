# Educational App Droid Quiz

This is an educational project aimed at learning concepts related to saving data on android devices
using ORM Room. The app is based on chapters 7-11 of the book "Saving Data On Android" from
raywenderlich.com

## Functionality

A simple app that allows the user to answer some (in this case only 2) questions about IT. On first
launch, the user needs to prepopulate the database of the app by selecting a corresponding menu in
the app bar.

## Architecture

This app uses some of the Architecture Components:

- ViewModel
- LiveData
- Room

Main focus of the app is on implementing data storage by means of ORM Room. The app has an SQLLite
database named quiz_database.db. It contains two tables:

- Answer(answerId, questionId, isCorrect, text)
- Question(questionId, text, difficulty)

The tables have a relation of one-to-many: one question may contain many answers.

The app exports database schema to the app/schemas directory. That is specified in build.gragle file

```groovy
    kapt {
    arguments {
        arg("room.schemaLocation", "$projectDir/schemas".toString())
    }
}
```

The app has 4 migrations:
- version 1 to 2
- version 2 to 3
- version 1 to 3
- automigration from version 3 to 4

```text
<!-- Copyright (c) 2019 Razeware LLC

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.

  Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
  distribute, sublicense, create a derivative work, and/or sell copies of the
  Software in any work that is designed, intended, or marketed for pedagogical or
  instructional purposes related to programming, coding, application development,
  or information technology.  Permission for such use, copying, modification,
  merger, publication, distribution, sublicensing, creation of derivative works,
  or sale is expressly withheld.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
-->
```