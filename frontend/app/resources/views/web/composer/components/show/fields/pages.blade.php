<div class="relative mx-2">
  <span class="text-lg font-semibold">Pages</span>
  <button class="collapse_button absolute top-0 right-0 transition-transform duration-400 transform hover:text-gray-dark">
    @svg('heroicon-s-chevron-up', 'h-7 w-7')
  </button>
  <div class="relative overflow-hidden transition-max-h ease-out duration-300">
    <ul class="mt-4  text-base">
      @forelse($pages as $page)
        <li class="mb-4 flex flex-row justify-start">
          <div class="w-2/6">Page</div>
          <div class="w-4/6 font-thin">
            <a href='{{ $page }}' target="_blank" class="underline">
              {{ $page }}
            </a>
          </div>
        </li>
      @empty
        <li class="mb-4 flex flex-row justify-start">
          <div class="w-2/6">Pages</div>
          <div class="w-4/6 font-thin text-gray">No data available</div>
        </li>
      @endforelse
    </ul>
  </div>
</div>
