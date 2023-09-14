@extends('web.layout')

@section('title')
  <title> Create Musical Work </title>
@endsection

@section('scripts')
  <script src="{{ asset('js/createMusicalWork.js') }}" defer></script>
@endsection


@section('main')
  <section class="mx-6 mx-auto py-6 px-2 xl:px-4 max-w-screen-lg">
    <div class="border-l border-gray"></div>
    <div class="min-w-100">
      <div id="document-header" class="flex flex-row items-start justify-between">
        <div id="document-identity">
          <!--Title-->
          <div class="mb-1 w-100 text-3xl">
            Create Musical Work
          </div>
        </div>
      </div>

      <article class="mt-5 mb-6 max-w-screen-lg">
        <hr class="my-4">
        @include('web.musicalWork.components.form_fields.uri')
        <hr class="my-4">
        @include('web.musicalWork.components.form_fields.title')
      </article>

    </div>

    <div class="my-8 text-sm flex  float-right">
      <a class="mr-5 p-2 px-3 rounded border border-gray-dark" href="{{ env('APP_URL') }}"
      >
        Cancel
      </a>
      <button type="submit" id="save_button" class="create p-2 px-3 rounded bg-gray-dark text-white">Create Musical Work</button>
    </div>


  </section>

@endsection
