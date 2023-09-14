@extends('web.layout')

@section('title')
  <title>About Linked Classical</title>
@endsection

@section('scripts')
@endsection

@section('styles')
@endsection


@section('main')
<section class="mx-6 xl:mx-auto py-6 xl:px-4 max-w-screen-md">
  <!--Header-->
  <div class="mt-8 my-8 text-4xl text-center">
    Linked Classical
  </div>
  <div class="my-4">
    <span class="pb-1 font-bold text-xl">Project Description</span>
    <p class="py-1">
      Linked Classical is a semantic web application developed in the context of classical music. The application uses a dataset from DBTune Classical as the base knowledge base, and allows for its expansion through SPARQL federation.
    </p>
  </div>
</section>
@endsection
